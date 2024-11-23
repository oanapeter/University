using System;
using System.Net.Sockets;
using System.Threading.Tasks;

namespace PDP_Lab4
{
    public class HttpDownloaderTaskBased
    {
        private readonly HttpParser _parser;

        public HttpDownloaderTaskBased(HttpParser parser)
        {
            _parser = parser;
        }

        // Corrected: Specify 'Task' as the type for TaskCompletionSource
        public Task ConnectAsync(Socket socket, string host, int port)
        {
            var tcs = new TaskCompletionSource<object>(); // Use 'object' as the result type
            socket.BeginConnect(host, port, ar =>
            {
                try
                {
                    socket.EndConnect(ar);
                    tcs.SetResult(null); // No result to return, so pass 'null'
                }
                catch (Exception ex)
                {
                    tcs.SetException(ex);
                }
            }, null);
            return tcs.Task;
        }

        // Corrected: Specify 'int' as the type for TaskCompletionSource
        public Task<int> SendAsync(Socket socket, byte[] buffer)
        {
            var tcs = new TaskCompletionSource<int>(); // Specify 'int' as the type
            socket.BeginSend(buffer, 0, buffer.Length, SocketFlags.None, ar =>
            {
                try
                {
                    int bytesSent = socket.EndSend(ar);
                    tcs.SetResult(bytesSent); // Return the number of bytes sent
                }
                catch (Exception ex)
                {
                    tcs.SetException(ex);
                }
            }, null);
            return tcs.Task;
        }

        // Corrected: Specify 'int' as the type for TaskCompletionSource
        public Task<int> ReceiveAsync(Socket socket, byte[] buffer)
        {
            var tcs = new TaskCompletionSource<int>(); // Specify 'int' as the type
            socket.BeginReceive(buffer, 0, buffer.Length, SocketFlags.None, ar =>
            {
                try
                {
                    int bytesRead = socket.EndReceive(ar);
                    tcs.SetResult(bytesRead); // Return the number of bytes read
                }
                catch (Exception ex)
                {
                    tcs.SetException(ex);
                }
            }, null);
            return tcs.Task;
        }
    }
}
