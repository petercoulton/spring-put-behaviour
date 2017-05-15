package com.petercoulton.spring.putbehaviour;


import com.fasterxml.jackson.annotation.*;

import org.bson.types.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.*;

@Document
public class Blob {
	@Id
	private ObjectId id;

	private Map<String, Object> additionalFields = new HashMap<>();

	Blob() {
	}

	public ObjectId getId() {
		return id;
	}

	@JsonAnySetter
	public void setAdditionalFields(final String key, final Object value) {
		this.additionalFields.put(key, value);
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalFields() {
		return additionalFields;
	}
}
