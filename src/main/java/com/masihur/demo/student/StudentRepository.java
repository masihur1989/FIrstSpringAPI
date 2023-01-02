package com.masihur.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Data Access Layer
 *
 * @author Masihur R. Maruf
 * @created 1/1/23 8:45 PM
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * this will be interpreted as `SELECT * FROM student WHERE email = ?`
     * Other option is to use this annotation - @Query("SELECT s FROM Student s WHERE s.email = ?1"), this is JBQL not SQL.
     *
     * @param email
     * @return
     */
    Optional<Student> findStudentByEmail(String email);
}
