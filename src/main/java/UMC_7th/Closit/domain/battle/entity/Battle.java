package UMC_7th.Closit.domain.battle.entity;

import UMC_7th.Closit.domain.board.entity.Board;
import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Battle extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "battle_id")
    private Long id;

    @Column
    private String title;

    @Column
    private LocalDate deadline;

    @Column
    private Integer likeCount;

    @OneToMany(mappedBy = "battle", cascade = CascadeType.ALL)
    @Builder.Default
    private List<BattleLike> battleLikesList = new ArrayList<>();

    @OneToMany(mappedBy = "battle", cascade = CascadeType.ALL)
    @Builder.Default
    private List<BattleComment> battleCommentList = new ArrayList<>();

    @OneToMany(mappedBy = "battle", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Vote> voteList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id1")
    private Post post1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id2")
    private Post post2;

    public void setPost2 (Post post2) { // 배틀 신청
        this.post2 = post2;
    }

    public boolean availableVote () { // 배틀 투표
        return LocalDate.now().isAfter(deadline);
    }

    public void increaseLikeCount() { // 배틀 좋아요 생성
        if (this.likeCount == null) {
            this.likeCount = 1;
        } else {
            this.likeCount++;
        }
    }

    public void decreaseLikeCount() { // 배틀 좋아요 삭제
        if (this.likeCount == null) {
            this.likeCount = 0;
        } else {
            this.likeCount--;
        }
    }
}
