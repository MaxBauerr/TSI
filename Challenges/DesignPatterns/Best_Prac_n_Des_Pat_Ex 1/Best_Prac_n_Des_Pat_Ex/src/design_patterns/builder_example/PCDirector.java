package design_patterns.builder_example;

/*
Director classes are sometimes included as part of the builder pattern, and other times not. Often, the 'client' is
also the director - as is the case with the built-in StringBuilder class.
We may use directors where the steps needed to be taken to build the object are always the same, in pursuit of DRY code.
*/
public class PCDirector {

    public void construct(PCBuilder builder) {
        builder.buildCPU();
        builder.buildGPU();
        builder.buildRAM();
    }
}
