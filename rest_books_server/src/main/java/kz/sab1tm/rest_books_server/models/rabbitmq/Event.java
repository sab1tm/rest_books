package kz.sab1tm.rest_books_server.models.rabbitmq;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by Sabit Murzaliev on 11.09.2021 12:04
 */

@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Event.class)
public class Event implements Serializable {

    private String username;
    private String actionDt;
    private String method;
    private String endpoint;
    private String params;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActionDt() {
        return actionDt;
    }

    public void setActionDt(String actionDt) {
        this.actionDt = actionDt;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "Event {" +
                "username='" + username + '\'' +
                ", actionDt=" + actionDt +
                ", method='" + method + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", params='" + params + '\'' +
                '}';
    }
}
