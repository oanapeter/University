using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace PDP_Lab4
{
    internal class Program
    {
        static async Task Main(string[] args)
        {
            // List of URLs you want to download files from
            var urls = new[]
            {
                "https://jsonplaceholder.typicode.com/posts/1",
                "https://jsonplaceholder.typicode.com/posts/2",
                "https://jsonplaceholder.typicode.com/posts/3"
            };

            var parser = new HttpParser();

            Console.WriteLine("Testing Event-Driven Implementation with multiple downloads:");
            var eventDrivenDownloader = new HttpDownloaderEventDriven(parser);
            await TestEventDrivenDownloader(eventDrivenDownloader, urls);

            Console.WriteLine("Testing Task-Based Implementation with multiple downloads:");
            var taskBasedDownloader = new HttpDownloaderTaskBased(parser);
            await TestTaskBasedDownloader(taskBasedDownloader, urls);

            Console.WriteLine("Testing Async/Await Implementation with multiple downloads:");
            var asyncAwaitDownloader = new HttpDownloaderAsyncAwait(new HttpDownloaderTaskBased(parser), parser);
            await TestAsyncAwaitDownloader(asyncAwaitDownloader, urls);

            Console.WriteLine("All downloads completed.");
            Console.ReadLine();
        }

        // Event-Driven download test
        static async Task TestEventDrivenDownloader(HttpDownloaderEventDriven downloader, string[] urls)
        {
            var downloadTasks = new List<Task>();

            foreach (var url in urls)
            {
                downloadTasks.Add(DownloadFileEventDriven(downloader, url));  // Initiate the download
            }

            // Wait for all the download tasks to finish
            await Task.WhenAll(downloadTasks);
        }

        // Helper method to download a single file using Event-Driven style
        static async Task DownloadFileEventDriven(HttpDownloaderEventDriven downloader, string url)
        {
            try
            {
                string savePath = $"output_event_{url.Split('/').Last()}.txt";
                downloader.DownloadFile(url, savePath);
                Console.WriteLine($"Event-Driven download completed for {url}.");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error in Event-Driven download for {url}: {ex.Message}");
            }
        }

        // Task-Based download test
        static async Task TestTaskBasedDownloader(HttpDownloaderTaskBased downloader, string[] urls)
        {
            var downloadTasks = new List<Task>();

            foreach (var url in urls)
            {
                downloadTasks.Add(DownloadFileTaskBased(downloader, url));  // Initiate the download
            }

            // Wait for all the download tasks to finish
            await Task.WhenAll(downloadTasks);
        }

        // Helper method to download a single file using Task-Based style
        static async Task DownloadFileTaskBased(HttpDownloaderTaskBased downloader, string url)
        {
            try
            {
                string savePath = $"output_task_{url.Split('/').Last()}.txt";
                var uri = new Uri(url);
                var socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
                await downloader.ConnectAsync(socket, uri.Host, 80);

                string request = $"GET {uri.PathAndQuery} HTTP/1.1\r\nHost: {uri.Host}\r\nConnection: close\r\n\r\n";
                byte[] requestBytes = Encoding.ASCII.GetBytes(request);
                await downloader.SendAsync(socket, requestBytes);

                var buffer = new byte[8192];
                int bytesRead = await downloader.ReceiveAsync(socket, buffer);

                Console.WriteLine($"Task-Based download completed for {url}. ");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error in Task-Based download for {url}: {ex.Message}");
            }
        }

        // Async/Await download test
        static async Task TestAsyncAwaitDownloader(HttpDownloaderAsyncAwait downloader, string[] urls)
        {
            var downloadTasks = new List<Task>();

            foreach (var url in urls)
            {
                downloadTasks.Add(DownloadFileAsyncAwait(downloader, url));  // Initiate the download
            }

            // Wait for all the download tasks to finish
            await Task.WhenAll(downloadTasks);
        }

        // Helper method to download a single file using Async/Await style
        static async Task DownloadFileAsyncAwait(HttpDownloaderAsyncAwait downloader, string url)
        {
            try
            {
                string savePath = $"output_async_{url.Split('/').Last()}.txt";
                await downloader.DownloadFileAsync(url, savePath);
                Console.WriteLine($"Async/Await download completed for {url}.");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error in Async/Await download for {url}: {ex.Message}");
            }
        }
    }
}
