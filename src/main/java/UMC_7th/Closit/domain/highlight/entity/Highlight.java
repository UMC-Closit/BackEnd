package UMC_7th.Closit.domain.highlight.entity;

import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Highlight extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "highlight_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 15)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String thumbnail;

    @OneToMany(mappedBy = "highlight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HighlightPost> highlightPosts;

    public void updateHighlight(String title, String thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }
}
