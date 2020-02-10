# Encapsulating Invocation

>Command Pattern

## Home automation sample

To design the API for our new Home Automation Remote Control.
The remote control features seven programmable slots along with corresponding on/off buttons for each. It also has a global undo button.

We've got a simple remote with on and off buttons but a set of vendor classes that are quite diverse.

### Command object

All command objects implement the same interface, which consists of one method.

- Implementation Code [Command object](06_home_automation)

## The Command Pattern

A Command object encapsulates a request by binding together a set of actions on a specific receiver.
To achieve this, it packages the actions and the receiver up into an object that exposes just one method, execute(). This method causes the actions to be invoked one the receiver.

You can parameterize an object with a command.

### The Command Pattern - Class diagram

```java
// Command declares an interface for all commands
interface Command {
    execute()
    undo()
}

// the ConcreteCommand defines a binding between an action and a Receiver. The Invoker makes a request by calling execute() and the ConcreteCommand carries it out by calling one or more actions on the Receiver
class ConcreteCommand implements Command {
    // the execute method invokes the actions on the receiver needed to fulfill the request
    execute()
    undo()
}

// the Receiver knows how to perform the work needed to carry out the request. Any class can act as a Receiver
class Receiver {
    action()
}

// the Invoker holds a command and at some point asks the command to carry out a request by calling its execute () method
class Invoker {
    setCommand()
}

// the Client is responsible for creating a ConcreteCommand and setting its Receiver
class Client {}
```
