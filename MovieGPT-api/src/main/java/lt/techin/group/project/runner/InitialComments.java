package lt.techin.group.project.runner;

import lombok.AllArgsConstructor;
import lt.techin.group.project.model.Media;
import lt.techin.group.project.repository.MediaRepository;
import lt.techin.group.project.rest.CommentRequest;
import lt.techin.group.project.service.CommentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
@Order(3)
public class InitialComments implements CommandLineRunner {

    private MediaRepository mediaRepository;
    private CommentService commentService;

    @Override
    public void run(String... args) throws Exception {

        CommentRequest commentRequest = new CommentRequest();

        List<String> adminComments = List.of(
                "Absolutely loved it! A masterpiece of storytelling.",
                "The cinematography was breathtaking, truly a work of art.",
                "An engaging plot with fantastic character development.",
                "It had its moments, but overall, it fell short of expectations.",
                "A must-watch for anyone who loves a good thriller!",
                "The soundtrack was incredible and really set the mood.",
                "Not my cup of tea, but I can see why others might enjoy it.",
                "The performances were top-notch and incredibly moving.",
                "A visual spectacle that keeps you hooked from start to finish.",
                "It felt a bit too long, but the ending made up for it.",
                "An emotional rollercoaster, I laughed, cried, and cheered!",
                "Predictable at times, but still a fun watch.",
                "The twists and turns kept me on the edge of my seat.",
                "A unique concept executed brilliantly.",
                "I wouldn't watch it again, but it was decent for a one-time view.",
                "The humor was spot-on and had me laughing throughout.",
                "A powerful message delivered through an outstanding narrative.",
                "It dragged a bit in the middle, but the action sequences were epic.",
                "A heartfelt story that resonates with anyone who has a soul.",
                "The dialogue was a bit cheesy, but the visuals were stunning.",
                "An underrated gem that deserves more recognition.",
                "The lead actor carried the entire film with their stellar performance.",
                "A good mix of drama, action, and a sprinkle of romance.",
                "The ending left me speechless – what a twist!",
                "It's rare to see something this original and captivating.",
                "Not perfect, but it has a charm that’s hard to resist."
        );

        for (Media media : mediaRepository.findAll()) {
            commentRequest.setMediaId(media.getId());
            commentRequest.setUserId(1L);
            commentRequest.setUserComment(adminComments.get((int) (media.getId() - 1L)));
            commentService.addComment(commentRequest);
            if (media.getId() > adminComments.size()) {
                break;
            }
        }

        List<String> userComments = List.of(
                "This movie left me speechless with its brilliant storytelling, emotional depth, and memorable characters. A true masterpiece that deserves all the accolades.",
                "The cinematography in this film was absolutely stunning, with every frame looking like a piece of art. The director's vision truly shines through.",
                "The plot was deeply engaging and kept me hooked from start to finish. The pacing was perfect, and every twist was unexpected but satisfying.",
                "While the story was predictable at times, the incredible acting performances more than made up for it, especially the lead actor's breathtaking portrayal.",
                "This is one of those rare movies that blends action, drama, and heartfelt moments seamlessly, making it an unforgettable experience.",
                "The soundtrack was nothing short of extraordinary, perfectly complementing the film's atmosphere and elevating every emotional scene.",
                "A unique concept that was executed with such finesse. The way the narrative unfolds is both captivating and thought-provoking.",
                "I was pleasantly surprised by how deeply this movie resonated with me. The themes of love, loss, and redemption were handled so beautifully.",
                "Although the movie had a few slow moments, the epic climax and emotionally powerful ending made it all worth it.",
                "The characters felt so real and relatable, with each of their journeys being explored in depth. It’s a testament to great writing and direction.",
                "This film was an emotional rollercoaster, balancing moments of joy, sorrow, and triumph in a way that felt completely organic and real.",
                "The humor in this movie was spot-on, providing just the right amount of levity to balance the more intense and dramatic moments.",
                "The special effects in this film were groundbreaking, creating a world so immersive and believable that I forgot it was all fictional.",
                "The script was so well-written, with dialogue that felt natural and authentic. Every interaction added depth to the characters and story.",
                "A beautiful exploration of humanity and what it means to truly connect with others. Few films manage to be this heartfelt and profound.",
                "From the opening scene to the final frame, this movie kept me on the edge of my seat. It’s been a while since I’ve been this invested in a film.",
                "I can't get over how stunning the visuals in this movie were. Each shot was so meticulously crafted, it felt like watching a moving painting.",
                "The lead actor gave a performance of a lifetime, bringing such complexity and vulnerability to their role. I can’t imagine anyone else in the part.",
                "The world-building in this film was incredible, creating a setting that felt rich, detailed, and completely alive. I didn’t want to leave it.",
                "The emotional depth of this movie caught me off guard. It’s rare for a film to touch on such universal truths with this much honesty and care.",
                "Despite its long runtime, the movie never felt boring. Each scene served a purpose and contributed to the overall story beautifully.",
                "The themes in this movie were so relevant and timely, it felt like it was speaking directly to me. A truly powerful piece of art.",
                "The direction was flawless, guiding the audience through a complex narrative with ease. It’s clear the director had a strong vision for this story.",
                "The action sequences were some of the best I’ve seen in years. They were thrilling, well-choreographed, and visually spectacular.",
                "This film managed to combine a compelling story with breathtaking visuals and unforgettable performances, making it an instant classic.",
                "I haven’t stopped thinking about this movie since I watched it. Its themes, characters, and ending will stay with me for a long time."
        );

        for (Media media : mediaRepository.findAll()) {
            commentRequest.setMediaId(media.getId());
            commentRequest.setUserId(2L);
            commentRequest.setUserComment(userComments.get((int) (media.getId() - 1L)));
            commentService.addComment(commentRequest);
            if (media.getId() > userComments.size()) {
                break;
            }
        }

        List<String> user2Comments = List.of(
                "A truly gripping story with twists and turns that kept me entertained throughout.",
                "The visuals were stunning, and the story had just the right amount of depth to keep me hooked.",
                "An excellent blend of action and drama with performances that were spot on.",
                "While the pacing was uneven, the strong ending made up for any flaws.",
                "The soundtrack added so much emotion to the story—it was perfectly chosen for the film.",
                "The movie was a refreshing take on a genre that can often feel repetitive.",
                "I appreciated the complexity of the characters and the care put into their development.",
                "This movie had me laughing, crying, and rooting for the characters until the very end.",
                "The action sequences were thrilling and well-executed without being over the top.",
                "A simple yet beautifully told story that left me with a lot to think about.",
                "The performances were so raw and authentic that they pulled me deeper into the story.",
                "Though some parts dragged a bit, the overall experience was memorable and enjoyable.",
                "The world-building was impressive, and it really drew me into the film’s universe.",
                "The emotional beats were perfectly timed and hit me harder than I expected.",
                "The movie’s message was inspiring and relevant, delivered without being preachy.",
                "A fascinating exploration of relationships and the complexities of human nature.",
                "The attention to detail in the cinematography was incredible and truly enhanced the experience.",
                "I loved how the humor was woven naturally into the story without feeling forced.",
                "The suspense kept me guessing, and the payoff was well worth the wait.",
                "It’s rare to find a film that balances heart and action this seamlessly.",
                "This movie had a unique charm that made it impossible not to enjoy.",
                "A thoughtful and heartfelt story that will stick with me for a long time.",
                "While not groundbreaking, it was a solid and enjoyable watch from start to finish.",
                "The chemistry between the leads brought the story to life in such an engaging way.",
                "The narrative unfolded beautifully, and the conclusion was both satisfying and poignant.",
                "This is the kind of movie you can watch again and still notice something new."
        );

        for (Media media : mediaRepository.findAll()) {
            commentRequest.setMediaId(media.getId());
            commentRequest.setUserId(3L);
            commentRequest.setUserComment(user2Comments.get((int) (media.getId() - 1L)));
            commentService.addComment(commentRequest);
            if (media.getId() > user2Comments.size()) {
                break;
            }
        }
    }
}
