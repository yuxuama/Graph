package utils.maths.graphs.exceptions;

public class wrongSizeException extends Exception {
    public wrongSizeException(String errorMessage){
        super(errorMessage);
    }

    public wrongSizeException(){
        super();
    }
}
