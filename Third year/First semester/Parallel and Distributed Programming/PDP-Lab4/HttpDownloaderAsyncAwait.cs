using System;
using System.IO;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace PDP_Lab4
{
    public class HttpDownloaderAsyncAwait
    {
        private readonly HttpDownloaderTaskBased _taskDownloader;
        private readonly HttpParser _parser;

        public HttpDownloaderAsyncAwait(HttpDownloaderTaskBased taskDownloader, HttpParser parser)
        {
            _taskDownloader = taskDownloader;
            _parser = parser;
        }

        public async Task DownloadFileAsync(string url, string savePath)
        {
            var uri = new Uri(url);
            var request = $"GET {uri.PathAndQuery} HTTP/1.1\r\nHost: {uri.Host}\r\nConnection: close\r\n\r\n";
            var requestBytes = Encoding.ASCII.GetBytes(request);

            // Using the traditional 'using' statement for resources
            using (var socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp))
            {
                await _taskDownloader.ConnectAsync(socket, uri.Host, 80);
                await _taskDownloader.SendAsync(socket, requestBytes);

                var buffer = new byte[8192];
                var response = new MemoryStream();

                while (true)
                {
                    var bytesRead = await _taskDownloader.ReceiveAsync(socket, buffer);
                    if (bytesRead <= 0) break;
                    response.Write(buffer, 0, bytesRead);
                }

                response.Position = 0;
                using (var reader = new StreamReader(response))
                {
                    var headers = reader.ReadLine();
                    Console.WriteLine($"Received Headers: {headers}");
                }
            }
        }
    }
}
