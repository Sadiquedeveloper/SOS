/*
 * Copyright (C) 2012-2018 52°North Initiative for Geospatial Open Source
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
package org.n52.sos.ds.cache;

import java.util.Locale;

import org.n52.iceland.i18n.I18NDAORepository;
import org.n52.iceland.ogc.ows.OwsServiceMetadataRepository;
import org.n52.series.db.HibernateSessionStore;
import org.n52.sos.ds.cache.base.FeatureOfInterestCacheUpdate;
import org.n52.sos.ds.cache.base.I18NCacheUpdate;
import org.n52.sos.ds.cache.base.ObservablePropertiesCacheUpdate;
import org.n52.sos.ds.cache.base.ObservationTimeCacheUpdate;
import org.n52.sos.ds.cache.base.OfferingCacheUpdate;
import org.n52.sos.ds.cache.base.ProcedureCacheUpdate;
import org.n52.sos.ds.cache.base.RelatedFeaturesCacheUpdate;

/**
 *
 * Fills the initial cache.
 * <p/>
 *
 * @see CompositePhenomenonCacheUpdate
 * @see ObservationTimeCacheUpdate
 * @see FeatureOfInterestCacheUpdate
 * @see ObservablePropertiesCacheUpdate
 * @see OfferingCacheUpdate
 * @see ProcedureCacheUpdate
 * @see RelatedFeaturesCacheUpdate
 * @see ResultTemplateCacheUpdate
 * @see I18NCacheUpdate
 * @author Christian Autermann <c.autermann@52north.org>
 *
 * @since 4.0.0
 */
public class InitialCacheUpdate extends CompositeCacheUpdate {

    public InitialCacheUpdate(int threadCount,
                              Locale defaultLocale,
                              I18NDAORepository i18NDAORepository,
                              HibernateSessionStore sessionStore,
                              OwsServiceMetadataRepository serviceMetadataRepository) {
        //execute all updates except offerings and procedures in parallel, then execute offering and procedure updates
        //(which spawn their own threads)
        super(new ParallelCacheUpdate(threadCount,
                                      sessionStore,
                                      new ObservablePropertiesCacheUpdate(),
                                      new FeatureOfInterestCacheUpdate(),
                                      new RelatedFeaturesCacheUpdate(),
                                      new ObservationTimeCacheUpdate()),
              new I18NCacheUpdate(serviceMetadataRepository,
                                  i18NDAORepository),
              new OfferingCacheUpdate(threadCount,
                                      defaultLocale,
                                      i18NDAORepository,
                                      sessionStore),
              new ProcedureCacheUpdate(threadCount, sessionStore));
    }
}
