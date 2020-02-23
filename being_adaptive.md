# Being Adaptive

The OO adapters take an interface and adapt it to one that a client is expecting.
The adapter as the middleman by receiving request from the client and converting them into requests that make sense on the vendor classes.

- Implementation Code [Duck adapter](07_duck_adapter)

## The Adapter Pattern explained

- the client makes a request to the adapter by calling a method on it using the target interface
- the adapter translates that request into one or more calls on the adaptee using the adaptee interface
- the client receives the results of the call and never knows there is an adapter doing the translation

Note that the Client ad Adapter are decoupled: neither knows about the other.

- Definition of the Adapter Pattern
