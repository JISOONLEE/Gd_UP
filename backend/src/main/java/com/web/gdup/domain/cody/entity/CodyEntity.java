package com.web.gdup.domain.cody.entity;

import com.web.gdup.domain.image.entity.ImageEntity;
import lombok.*;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "cody")
@Builder
@Log
@EntityListeners(AuditingEntityListener.class)
public class CodyEntity {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codyid")
    int codyId;
    @Column(name = "codyname")
    String codyName;
    @Column(name = "registrationdate")
    LocalDateTime registrationDate;
    @Column(name = "updatedate")
    LocalDateTime updateDate;
    String content;
    @Column(name = "username")
    String userName;
    @Column(name = "secret")
    int secret;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "imageid")
    private ImageEntity imageModel;

}