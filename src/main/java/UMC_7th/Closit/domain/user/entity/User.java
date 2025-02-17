package UMC_7th.Closit.domain.user.entity;

import UMC_7th.Closit.domain.battle.entity.BattleComment;
import UMC_7th.Closit.domain.battle.entity.BattleLike;
import UMC_7th.Closit.domain.battle.entity.Vote;
import UMC_7th.Closit.domain.follow.entity.Follow;
import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.notification.entity.Notification;
import UMC_7th.Closit.domain.post.entity.Bookmark;
import UMC_7th.Closit.domain.post.entity.Comment;
import UMC_7th.Closit.domain.post.entity.Likes;
import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.global.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // USER, ADMIN

    @Column(length = 20, nullable = false, unique = true)
    private String clositId;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @Column
    private LocalDate birth;

    @Column
    private String profileImage;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Likes> likesList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Bookmark> bookmarkList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Highlight> highlightList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Vote> voteList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<BattleComment> battleCommentList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<BattleLike> battleLikesList = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Follow> followerList = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Follow> followingList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Notification> notificationList = new ArrayList<>();

    public User updateRole(Role newRole) {
        this.role = newRole;
        return this;
    }

    public User updateProfileImage(String profileImage) {
        this.profileImage = profileImage;
        return this;
    }

    public void setName(@Size(min = 2, max = 20, message = "이름은 2~20자 사이여야 합니다.") String name) {
        this.name = name;
    }

    public void setClositId(@Size(min = 2, max = 20, message = "clositId는 2~20자 사이여야 합니다.") String clositId) {
        this.clositId = clositId;
    }

    public void setPassword(String encode) {
        this.password = encode;
    }

    public void setBirth(@PastOrPresent(message = "생년월일은 과거나 현재 날짜여야 합니다.") LocalDate birth) {
        this.birth = birth;
    }
}
