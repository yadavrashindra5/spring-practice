package com.practice.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Assets {
    @Id
    private String assetsId;
    private String title;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
