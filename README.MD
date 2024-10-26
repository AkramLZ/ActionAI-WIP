# ActionAI
<center>Let Large Language Models perform any actions!</center>

## Backstory
I was thinking by myself and had a random idea "What if Large Language Models can perform actions?"<br>
So, that's how the project came from

## Custom Action Implementation
Actions can be implemented by implementing `LLMAction` Interface like this:
```java
public interface SomeAction implements LLMAction {
    
    @Override
    public String id() {
        return "HELLO_WORLD";
    }

    /**
     * Description must be accurate and can be understood by AI model.
     */
    @Override
    public String description() {
        return "Prints hello world in terminal.";
    }
    
    @Override
    public Runnable process() {
        return () -> System.out.println("Hello, World!");
    }
    
}
```

Then you can register it using `ActionAI#registerActions` function.

Have fun!