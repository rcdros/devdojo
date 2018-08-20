package br.com.devdojo.error;

/**
 * Created by ricardors on 06/08/2018.
 */
public class ErrorDetails {
    private String title;
    private int status;
    private String Details;
    private long timeStamp;
    private String message;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public static final class ErrorDetailsBuilder {
        private String title;
        private int status;
        private String Details;
        private long timeStamp;
        private String message;

        private ErrorDetailsBuilder() {
        }

        public static ErrorDetailsBuilder newBuilder() {
            return new ErrorDetailsBuilder();
        }

        public ErrorDetailsBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ErrorDetailsBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ErrorDetailsBuilder Details(String Details) {
            this.Details = Details;
            return this;
        }

        public ErrorDetailsBuilder timeStamp(long timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public ErrorDetailsBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorDetails build() {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setTitle(title);
            errorDetails.setStatus(status);
            errorDetails.setDetails(Details);
            errorDetails.setTimeStamp(timeStamp);
            errorDetails.setMessage(message);
            return errorDetails;
        }
    }
}
