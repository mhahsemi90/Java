package calculation.services.dto.entity;

import calculation.repository.entity.Element;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ElementTypeDto {
    private Long id;
    private String code;
    private String title;
    private List<Element> elementList = new ArrayList<>();
}
