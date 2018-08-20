package br.com.devdojo.error;

/**
 * Created by ricardors on 06/08/2018.
 */
public class ValidationErrorDetails extends ErrorDetails {
    private String field;
    private String fieldMessage;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

    public void setFieldMessage(String fieldMessage) {
        this.fieldMessage = fieldMessage;
    }

    public static final class Builder {
        private String title;
        private int status;
        private String Details;
        private long timeStamp;
        private String message;
        private String field;
        private String fieldMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder Status(int status) {
            this.status = status;
            return this;
        }

        public Builder Details(String Details) {
            this.Details = Details;
            return this;
        }

        public Builder timeStamp(long timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder field(String field) {
            this.field = field;
            return this;
        }

        public Builder fieldMessage(String fieldMessage) {
            this.fieldMessage = fieldMessage;
            return this;
        }

        public ValidationErrorDetails build() {
            ValidationErrorDetails validationNotFoundDetails = new ValidationErrorDetails();
            validationNotFoundDetails.setMessage(this.message);
            validationNotFoundDetails.setDetails(this.Details);
            validationNotFoundDetails.setTitle(this.title);
            validationNotFoundDetails.setStatus(this.status);
            validationNotFoundDetails.setTimeStamp(this.timeStamp);
            validationNotFoundDetails.setField(this.field);
            validationNotFoundDetails.setFieldMessage(this.fieldMessage);
            return validationNotFoundDetails;
        }
    }

}
