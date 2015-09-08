/**
 * Copyright (C) 2012-2015 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 as published
 * by the Free Software Foundation.
 *
 * If the program is linked with libraries which are licensed under one of
 * the following licenses, the combination of the program with the linked
 * library is not considered a "derivative work" of the program:
 *
 *     - Apache License, version 2.0
 *     - Apache Software License, version 1.0
 *     - GNU Lesser General Public License, version 3
 *     - Mozilla Public License, versions 1.0, 1.1 and 2.0
 *     - Common Development and Distribution License (CDDL), version 1.0
 *
 * Therefore the distribution of the program linked with libraries licensed
 * under the aforementioned licenses, is permitted by the copyright holders
 * if the distribution is compliant with both the GNU General Public
 * License version 2 and the aforementioned licenses.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 */
package org.n52.sos.ds.hibernate.dao.observation;

import org.n52.iceland.ogc.om.OmConstants;
import org.n52.sos.ds.hibernate.entities.observation.ObservationVisitor;
import org.n52.sos.ds.hibernate.entities.observation.full.BlobObservation;
import org.n52.sos.ds.hibernate.entities.observation.full.BooleanObservation;
import org.n52.sos.ds.hibernate.entities.observation.full.CategoryObservation;
import org.n52.sos.ds.hibernate.entities.observation.full.ComplexObservation;
import org.n52.sos.ds.hibernate.entities.observation.full.CountObservation;
import org.n52.sos.ds.hibernate.entities.observation.full.GeometryObservation;
import org.n52.sos.ds.hibernate.entities.observation.full.NumericObservation;
import org.n52.sos.ds.hibernate.entities.observation.full.SweDataArrayObservation;
import org.n52.sos.ds.hibernate.entities.observation.full.TextObservation;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann
 */
public class ObservationTypeObservationVisitor implements ObservationVisitor<String> {

    private ObservationTypeObservationVisitor() {
    }

    @Override
    public String visit(NumericObservation o) {
        return OmConstants.OBS_TYPE_MEASUREMENT;
    }

    @Override
    public String visit(BlobObservation o) {
        return OmConstants.OBS_TYPE_UNKNOWN;
    }

    @Override
    public String visit(BooleanObservation o) {
        return OmConstants.OBS_TYPE_TRUTH_OBSERVATION;
    }

    @Override
    public String visit(CategoryObservation o) {
        return OmConstants.OBS_TYPE_CATEGORY_OBSERVATION;
    }

    @Override
    public String visit(ComplexObservation o) {
        return OmConstants.OBS_TYPE_COMPLEX_OBSERVATION;
    }

    @Override
    public String visit(CountObservation o) {
        return OmConstants.OBS_TYPE_COUNT_OBSERVATION;
    }

    @Override
    public String visit(GeometryObservation o) {
        return OmConstants.OBS_TYPE_GEOMETRY_OBSERVATION;
    }

    @Override
    public String visit(TextObservation o) {
        return OmConstants.OBS_TYPE_TEXT_OBSERVATION;
    }

    @Override
    public String visit(SweDataArrayObservation o) {
        return OmConstants.OBS_TYPE_SWE_ARRAY_OBSERVATION;
    }

    public static ObservationTypeObservationVisitor getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final ObservationTypeObservationVisitor INSTANCE
                = new ObservationTypeObservationVisitor();

        private Holder() {
        }
    }

}