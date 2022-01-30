Real Time Quote Server:
There are 3 approaches which I have assume for this:
1.	Server sends Quotation to client every 100ms and the client prints data.
2.	Client requests from server every 100ms.
3.	Server sends Quotation only when client requests it.
The 1st and 2nd can be clubbed to the same server. 
The Client will connect to the server on a port number, right now on local we are running the server at 0.0.0.0 but this can vary based on the server location.
A random Quote message is generate in 2 ways:
1.	The Client sends a symbol to Server and then its read by Server and a Quotation is provided for that Symbol.
2.	The Server auto-generates the Symbol using a library called faker. And then provides the quotation for those symbols. (The client can also use faker to request symbol quotations from the server).
When client is given control of the quotation input it acts like a buffer so that the client only receives only what is asked.
In the approach where Quotation is generated by server, the client can crash if it’s not able to handle flow of incoming messages. Both the cases have been implemented.
 ![image](https://user-images.githubusercontent.com/26936240/151702145-d0f2aee3-d62b-4f84-9757-0d2d82c71e82.png)

We can connect multiple clients to the server as the server creates a new thread every time a client connects to it.
