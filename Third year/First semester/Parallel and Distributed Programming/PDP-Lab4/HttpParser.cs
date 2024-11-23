using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PDP_Lab4
{
    public class HttpParser
    {
        public int ParseContentLength(string headers)
        {
            var lines = headers.Split(new[] { "\r\n" }, StringSplitOptions.RemoveEmptyEntries);
            foreach (var line in lines)
            {
                if (line.StartsWith("Content-Length:", StringComparison.OrdinalIgnoreCase))
                {
                    var parts = line.Split(':');
                    if (parts.Length > 1 && int.TryParse(parts[1].Trim(), out int contentLength))
                    {
                        return contentLength;
                    }
                }
            }
            return 0; // Default if Content-Length is not found
        }
    }

}
