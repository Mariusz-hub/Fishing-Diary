package com.fisher.exceptions;


public class ExceptionBody {

        private String message;
        private String timestamp;
        private String path;
        private int status;

    public ExceptionBody(Builder builder) {
        this.message = builder.message;
        this.timestamp = builder.timestamp;
        this.path = builder.path;
        this.status = builder.status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ExceptionBody{" +
                "message='" + message + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", path='" + path + '\'' +
                ", status=" + status +
                '}';
    }

    public static class Builder {

        private String message;
        private String timestamp;
        private String path;
        private int status;

        public Builder message (String message){
            this.message = message;
            return this;
        }

        public Builder timesstamp (String timestamp){
            this.timestamp = timestamp;
            return this;
        }

        public Builder path (String path){
            this.path = path;
            return this;
        }

        public Builder status (int status){
            this.status = status;
            return this;
        }

        public ExceptionBody build(){
            return new ExceptionBody(this);
        }

    }
}
