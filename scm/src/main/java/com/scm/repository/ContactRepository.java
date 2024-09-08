package com.scm.repository;

import com.scm.entities.Contact;
import com.scm.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,String> {
    // find  all contacts of user
    List<Contact> findByUser(User user);

    @Query("SELECT c FROM Contact c JOIN c.user u WHERE u.userId = :userId")
   List<Contact> findByUserId(@Param("userId") String userId);
}
