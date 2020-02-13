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

## Remote Control Application

- Implementation Code [Remote Control](06_home_automation)

The NoCommand object is an example of a null object.
A null object is useful when you don't have a meaningful object to return, and yet you want to remove the responsibility for handling null from the client.

### Remote Control Application - class diagram

```java
// all RemoteControl commands implement the Command interface
// commands encapsulate a set of actions on a specific vendor class
// the remote invokes these actions by calling the execute() method
interface Command {
    execute()
}

// manages a set of Command objects, one per button
// when button is pressed, the corresponding ButtonWasPushed() method is called, which invokes the execute() method on the command
class RemoteControl {
    onCommands
    offCommands

    setCommand()
    onButtonWasPushed()
    offButtonWasPushed()
}

// creates a number of Command Objects that are loaded into the slots of the Remote Control
class RemoteLoader {}

// the Vendor Classes are used to perform the actual home-automation work of controlling devices
class Light {
    on()
    off()
}

// each action that can be inviked by pressing a button on the remote is implemented with a simple Command object
class LightOnCommand implements Command {
    execute()
}
class LightOffCommand implements Command [
    execute()
]
```
