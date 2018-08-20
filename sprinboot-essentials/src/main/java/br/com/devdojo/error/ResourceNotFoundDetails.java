package br.com.devdojo.error;

/**
 * Created by ricardors on 05/08/2018.
 */
public class ResourceNotFoundDetails extends ErrorDetails  {

    public static final class Builder {
        private String title;
        private int status;
        private String Details;
        private long timeStamp;
        private String message;

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

        public ResourceNotFoundDetails build() {
            ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
            resourceNotFoundDetails.setMessage(this.message);
            resourceNotFoundDetails.setDetails(this.Details);
            resourceNotFoundDetails.setTitle(this.title);
            resourceNotFoundDetails.setStatus(this.status);
            resourceNotFoundDetails.setTimeStamp(this.timeStamp);
            return resourceNotFoundDetails;
        }
    }
}
