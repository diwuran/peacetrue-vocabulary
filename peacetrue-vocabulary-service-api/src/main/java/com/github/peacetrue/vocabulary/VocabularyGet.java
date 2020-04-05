package com.github.peacetrue.vocabulary;

import com.github.peacetrue.core.OperatorCapableImpl;
import lombok.*;

import javax.validation.constraints.NotNull;


/**
 * @author xiayx
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VocabularyGet extends OperatorCapableImpl<String> {

    private static final long serialVersionUID = 0L;

    @NotNull
    private Long id;

}
