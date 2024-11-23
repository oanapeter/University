using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace PDP_Lab4
{
    public class HttpDownloaderEventDriven
    {
        private readonly HttpParser _parser;

        public HttpDownloaderEventDriven(HttpParser parser)
        {
            _parser = parser;
        }

        public void DownloadFile(string url, string savePath)
        {
            var uri = new Uri(url);
            var request = $"GET {uri.PathAndQuery} HTTP/1.1\r\nHost: {uri.Host}\r\nConnection: close\r\n\r\n";
            var requestBytes = Encoding.ASCII.GetBytes(request);

            var socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);

            socket.BeginConnect(uri.Host, 80, ar =>
            {
                try
                {
                    socket.EndConnect(ar);
                    socket.BeginSend(requestBytes, 0, requestBytes.Length, SocketFlags.None, sendAr =>
                    {
                        try
                        {
                            socket.EndSend(sendAr);
                            var buffer = new byte[8192];
                            var response = new StringBuilder();

                            void ReceiveCallback(IAsyncResult receiveAr)
                            {
                                try
                                {
                                    var bytesRead = socket.EndReceive(receiveAr);
                                    if (bytesRead > 0)
                                    {
                                        response.Append(Encoding.ASCII.GetString(buffer, 0, bytesRead));
                                        socket.BeginReceive(buffer, 0, buffer.Length, SocketFlags.None, ReceiveCallback, null);
                                    }
                                    else
                                    {
                                        var headersEndIndex = response.ToString().IndexOf("\r\n\r\n", StringComparison.Ordinal);
                                        var headers = response.ToString(0, headersEndIndex + 4);
                                        var contentLength = _parser.ParseContentLength(headers);
                                        Console.WriteLine($"Content-Length: {contentLength}");
                                        socket.Close();
                                    }
                                }
                                catch (Exception ex)
                                {
                                    Console.WriteLine($"Error receiving data: {ex.Message}");
                                    socket.Close();
                                }
                            }

                            socket.BeginReceive(buffer, 0, buffer.Length, SocketFlags.None, ReceiveCallback, null);
                        }
                        catch (Exception ex)
                        {
                            Console.WriteLine($"Error sending data: {ex.Message}");
                            socket.Close();
                        }
                    }, null);
                }
                catch (Exception ex)
                {
                    Console.WriteLine($"Error connecting: {ex.Message}");
                    socket.Close();
                }
            }, null);
        }
    }
}