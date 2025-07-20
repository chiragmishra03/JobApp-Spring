package com.app.jobApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    private String linkedInUrl;

    private String githubUrl;

    @Column(name = "resume_url")
    private String resumeUrl;

    @Column(name = "acquired_experience")
    private int acqExperience;

    @Column(name = "profile_description")
    private String profileDescription;

    @ElementCollection
    @CollectionTable(name = "applicant_skills", joinColumns = @JoinColumn(name = "applicant_id"))
    @Column(name = "skill")
    private List<String> skills;

    @Lob
    @Column(name = "profile_image", columnDefinition = "BYTEA")
    private byte[] profileImage;

    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    AddressDetails addressDetails ;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


}
