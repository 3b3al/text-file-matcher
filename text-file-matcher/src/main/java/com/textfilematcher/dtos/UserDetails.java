package com.textfilematcher.dtos;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface UserDetails {

   User loadUserByEmail(String email) throws NotFoundException;


}
