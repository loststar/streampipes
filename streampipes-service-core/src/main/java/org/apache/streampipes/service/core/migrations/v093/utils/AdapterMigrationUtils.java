/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.streampipes.service.core.migrations.v093.utils;

import org.apache.streampipes.service.core.migrations.v093.migrator.AdapterMigrator;
import org.apache.streampipes.service.core.migrations.v093.migrator.GenericAdapterMigrator;
import org.apache.streampipes.service.core.migrations.v093.migrator.MigrationHelpers;
import org.apache.streampipes.service.core.migrations.v093.migrator.SpecificAdapterMigrator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdapterMigrationUtils {

  private static final String GENERIC_SET =
      "org.apache.streampipes.model.connect.adapter.GenericAdapterSetDescription";
  private static final String SPECIFIC_SET =
      "org.apache.streampipes.model.connect.adapter.SpecificAdapterSetDescription";
  private static final String GENERIC_STREAM =
      "org.apache.streampipes.model.connect.adapter.GenericAdapterStreamDescription";
  private static final String SPECIFIC_STREAM =
      "org.apache.streampipes.model.connect.adapter.SpecificAdapterStreamDescription";

  public static final String NEW_MODEL =
      "org.apache.streampipes.model.connect.adapter.AdapterDescription";
  public static List<String> deprecatedAdapterSetClasses = List.of(
      GENERIC_SET,
      SPECIFIC_SET
  );

  public static List<String> deprecatedAdapterStreamClasses = List.of(
      GENERIC_STREAM,
      SPECIFIC_STREAM
  );

  public static List<String> deprecatedAdapterClasses =
      Stream.concat(
              deprecatedAdapterSetClasses.stream(),
              deprecatedAdapterStreamClasses.stream())
          .collect(Collectors.toList());

  public static AdapterMigrator getAdapterMigrator(String adapterType) {
    if (adapterType.equals(GENERIC_STREAM)) {
      return new GenericAdapterMigrator(new MigrationHelpers());
    } else {
      return new SpecificAdapterMigrator(new MigrationHelpers());
    }
  }

}
