package com.petercoulton.spring.putbehaviour;

import org.bson.types.*;
import org.springframework.data.repository.*;

public interface BlobRepository extends CrudRepository<Blob, ObjectId> {
}
