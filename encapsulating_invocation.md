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

### Remote Control Application - add functionality

We need to add functionality to support the undo button on the remote.

Steps:

- when commands support undo, they have an undo() method that mirrors the execute() method. Before we can add undo to our commands, we need to add an undo() method to the Command interface:

```java
public interface Command {
    public void execute();
    public void undo();
}
```

- if the LightOnCommand's execute() method was called, then the on() method was last called. We know that undo() needs to do the opposite of this by calling the off() method:

```java
public class LightOnCommand implements Command {
    // ...
    public void undo() {
        light.off();
    }
}

public class LightOffCommand implements Command {
    // ...
    public void undo() {
        light.on();
    }
}
```

- to add support for the undo button we only have to make a few small changes to the Remote Control class..
We'll add a new instance variable to track the last command invoked; then, whenever the undo button is pressed, we retrieve that command and invoke its undo() method:

```java
public class RemoteControlWithUndo {
    // ...
    // thisi is where we'll stash the last command executed for the undo button
    Command undoCommmand;

    public RemoteControlWithUndo() {
        // ...
        undoCommand = noCommand;
    }

    // ...
    // when a button is pressed we take the command and first execute it
    // then we save a reference to it in the undoCommand instance variable
    public void onButtonWasPushed(int slot) {
        // ...
        undoCommand = onCommands[slot];
    }
    public void offButtonWasPushed(int slot) {
        // ...
        undoCommand = offCommands[slot];
    }

    // when the undo button is pressed, we invoke the undo() method of the command stored in undoCommand
    public void undoButtonWasPushed() {
        undoCommand.undo();
    }

    // ...
}
```

- Implementation Code [Remote Control With Undo](06_home_automation)
- Implementation Code [Remote Control With Undo - using state](06_home_automation)

### Remote Control Application - Party mode with macro

Push one button and have the lights dimmed, the stereo and TV turned on and set to a DVD and the hot tub fired up.

```java
// without changing the remote at all with the macro command
public class MacroCommand implements Command {
    Command[] commands;

    public MacroCommand(Command[] commands) {
        // take an array of Commands and store them in the MacroCommand
        this.commands = commands;
    }

    public void execute() {
        for (int i = 0; i < commands.lenght; i++) {
            // when the macro gets executed by the remote execute those commands one at a time
            commands[i].execute();
        }
    }

    public void undo() {
        for (int i = 0; i < commands.lenght; i++) {
            commands[i].undo();
        }
    }
}

// STEPS:

// 1) we create the set of commands we want to go into the macro:

// create all the devices
Light light = new Light("Living Room");
TV tv = new TV("Living Room");
Stereo stereo = new Stereo("Living Room");
Hottub hottub = new Hottub();

// create all the On commands
LightOnCommand lightOn = new LightOnCommand(light);
TVOnCommand tvOn = new TVOnCommand(tv);
StereoOnCommand stereoOn = new StereoOnCommand(stereo);
HottubOnCommand hottubOn = new HottubOnCommand(hottub);

// create all the Off commands
LightOffCommand lightOff = new LightOffCommand(light);
TVOffCommand tvOff = new TVOffCommand(tv);
StereoOffCommand stereoOff = new StereoOffCommand(stereo);
HottubOffCommand hottubOff = new HottubOffCommand(hottub);

// 2) we create two arrays, one for the On commands and one for the Off commands, and load them with the corresponding commands
Command[] partyOn = {lightOn, stereoOn, tvOn, hottubOn};
Command[] partyOff = {lightOff, stereoOff, tvOff, hottubOff};

MacroCommand partyOnMacro = new MacroCommand(partyOn);
MacroCommand partyOffMacro = new MacroCommand(partyOff);

// 3) we assign MacroCommand to a button like we always do
remoteControl.setCommand(0, partyOnMacro, partyOffMacro);

// 4) we just need to push some buttons and see if this works
System.out.println(remoteControl);
System.out.println("--- Pushing Macro On ---");
remoteControl.onButtonWasPushed(0);
System.out.println("--- Pushing Macro Off ---");
remoteControl.offButtonWasPushed(0);
```

## More uses of the Command Pattern: queuing requests

You add commands to the queue on one end, and on the other end sit a group of threads.
Threads run the following script:

- they remove a command from the queue
- call its execute() method
- wait for the call to finish
- then discard the command object and retrieve a new one

The job queue classes are totally decoupled from the objects that are doing the computation. They just retrieve commands and call execute().
Likewise, as long as you put objects into the queue that implement the Command Pattern, your execute() method will be invoked when a thread is available.

## More uses of the Command Pattern: logging requests

The semantics of some applications require that we log all actions and be able to recover after a crash by re-invoking those actions. The Command Pattern can support these semantics with the addition of two methods: store() and load().
As we execute commands, we store a history of them on disk. When a crash occurs, we reload the command objects and invoke their execute() methods in batch and in order.
