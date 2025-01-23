package UMC_7th.Closit.domain.battle.entity;

import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Vote extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Long id;

    @Column(nullable = false)
    private Long votedPostId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "battle_id")
    private Battle battle;

    public void setUser (Long userId) { // 배틀 투표
        this.user = user;
    }

    public void setBattle (Battle battle) {
        this.battle = battle;
    }

    public void setVotedPostId (Long postId) {
        this.votedPostId = votedPostId;
    }
}