package com.clinicservice.MultimediaServer.HttpResponse;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author HP
 */
public class ResponseUtils {
    /**
     * @author Areli Shalon Paredes Curasco
     * @param status
     * @param message
     * @param data
     * @param response
     * @return
     */
    public ResponseEntity<?> success(int status, String message, Object data, HttpServletResponse response) {
        return ResponseEntity.status(status).body(new ResponseData(status, message, data));
    }
    
    /**
     * @author Areli Shalon Paredes Curasco
     * @param status
     * @param message
     * @param response
     * @param data
     * @return
     */
    public ResponseEntity<?> ok(int status, String message, Object data, HttpServletResponse response) {
        return ResponseEntity.status(status).body(new ResponseData(status, message,data));
    }

    /**
     * @author Areli Shalon Paredes Curasco
     * @param status
     * @param message
     * @param response
     * @return
     */
    public ResponseEntity<?> error(int status, String message, HttpServletResponse response) {
        return ResponseEntity.status(status).body(new ResponseData(status, message, null));
    }
    

    private static class ResponseData   {
        private int status;
        private String message;
        private Object data;

        public ResponseData(int status, String message, Object data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

    }
}
