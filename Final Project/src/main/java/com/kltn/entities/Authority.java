package com.kltn.entities;

import com.kltn.Util.AuthorityName;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by TinNguyen on 6/4/17.
 */
@Document(collection = "Authority")
public class Authority {
    @Id
    private ObjectId id;

    private AuthorityName name;

    public Authority(AuthorityName name) {
        this.name = name;
    }

    public String getId() {
        return id.toString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public AuthorityName getName() {
        return name;
    }

    public void setName(AuthorityName name) {
        this.name = name;
    }
}
