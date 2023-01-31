package api.model;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {
    String firstName;
    String lastName;
    String description;
}
