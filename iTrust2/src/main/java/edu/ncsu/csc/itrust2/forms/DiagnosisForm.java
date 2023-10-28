package edu.ncsu.csc.itrust2.forms;

import edu.ncsu.csc.itrust2.models.Diagnosis;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Getter
public class DiagnosisForm implements Serializable {

    private Long visit;

    private String note;

    private Long id;

    private String code;

    public DiagnosisForm(@NotNull final Diagnosis diag) {
        /* May not be attached to a visit yet */
        if (null != diag.getVisit()) {
            visit = diag.getVisit().getId();
        }

        note = diag.getNote();
        id = diag.getId();
        code = diag.getCode().getCode();
    }
}
