package UMC_7th.Closit.domain.post.entity;

import UMC_7th.Closit.domain.mission.entity.Mission;
import UMC_7th.Closit.domain.battle.entity.Battle;
import UMC_7th.Closit.domain.todaycloset.entity.Todaycloset;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column
    private String postImage;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @OneToMany(mappedBy = "post1", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Battle> battleList1 = new ArrayList<>();

    @OneToMany(mappedBy = "post2", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Battle> battleList2 = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Todaycloset> todayclosetList = new ArrayList<>();
}