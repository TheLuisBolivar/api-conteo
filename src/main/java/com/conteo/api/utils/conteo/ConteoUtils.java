package com.conteo.api.utils.conteo;

import com.conteo.api.models.dtos.conteo.ProblemDtoRq;
import com.conteo.api.utils.conteo.ConteoConstants;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ConteoUtils {

    public String getCase(ProblemDtoRq problemDtoRq) {
        String result = "";
        log.info("[getCase]: ProblemDtoRq: {}", new Gson().toJson(problemDtoRq));

        if (Boolean.TRUE.equals(problemDtoRq.getEntranTodos())) {
            if (Boolean.TRUE.equals(problemDtoRq.getEsCircular()) &&
                    Boolean.TRUE.equals(problemDtoRq.getSeRepite())) {
                result = "";
            } else {
                if (Boolean.FALSE.equals(problemDtoRq.getImportaOrden())) {
                    result = "";
                } else {
                    if (Boolean.TRUE.equals(problemDtoRq.getSeRepite())) {
                        result = ConteoConstants.PERMUTACION_REPETICION;
                    } else {
                        result = ConteoConstants.PERMUTACION;
                    }

                    if (Boolean.TRUE.equals(problemDtoRq.getEsCircular())) {
                        result = ConteoConstants.PERMUTACION_CIRCULAR;
                    }
                }
            }
        } else {
            if (Boolean.TRUE.equals(problemDtoRq.getEsCircular())) {
                result = "";
            } else {
                if (Boolean.TRUE.equals(problemDtoRq.getImportaOrden())) {
                    if (Boolean.TRUE.equals(problemDtoRq.getSeRepite())) {
                        result = ConteoConstants.VARIACION_REPETICION;
                    } else {
                        result = ConteoConstants.VARIACION;
                    }
                } else {
                    if (Boolean.TRUE.equals(problemDtoRq.getSeRepite())) {
                        result = ConteoConstants.COMBINATORIA_REPETICION;
                    } else {
                        result = ConteoConstants.COMBINATORIA;
                    }
                }
            }
        }

        log.info("[getCase]: CASE: {}", result);
        return result;
    }
}
