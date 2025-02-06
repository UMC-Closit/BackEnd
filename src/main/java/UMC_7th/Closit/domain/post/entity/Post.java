package UMC_7th.Closit.domain.post.entity;

import UMC_7th.Closit.domain.mission.entity.Mission;
import UMC_7th.Closit.domain.battle.entity.Battle;
import UMC_7th.Closit.domain.todaycloset.entity.TodayCloset;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String frontImage;

    @Column(nullable = false)
    private String backImage;

    @Column(nullable = false)
    private boolean isBattle;

    @Column
    private Integer votingCount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    @Column(nullable = false)
    private String pointColor;

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
    private List<TodayCloset> todayclosetList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Likes> likesList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarkList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostHashTag> postHashTagList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemTag> itemTagList = new ArrayList<>();

    public void isBattle(boolean isBattle) { // 배틀 생성
        this.isBattle = isBattle;
    }

    public void incrementVotingCount() { // 배틀 투표
        if (this.votingCount == null) {
            this.votingCount = 1;
        } else {
            this.votingCount += votingCount;
        }
    }
}
