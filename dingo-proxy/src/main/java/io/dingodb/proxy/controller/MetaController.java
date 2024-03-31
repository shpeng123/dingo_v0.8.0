/*
 * Copyright 2021 DataCanvas
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.dingodb.proxy.controller;

import io.dingodb.client.DingoClient;
import io.dingodb.common.table.TableDefinition;
import io.dingodb.sdk.common.table.Table;
import io.dingodb.proxy.mapper.EntityMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Meta")
@RestController
@RequestMapping("/meta")
public class MetaController {

    @Autowired
    private DingoClient dingoClient;

    @Autowired
    private EntityMapper mapper;

    @ApiOperation("Create table")
    @PutMapping("/api/{schema}/{table}")
    public ResponseEntity<Boolean> crateTable(@PathVariable String schema, @PathVariable String table, @RequestBody TableDefinition tableDefinition) {
        return ResponseEntity.ok(dingoClient.createTable(schema, mapper.mapping(tableDefinition)));
    }

    @ApiOperation("Drop table")
    @DeleteMapping("/api/{schema}/{table}")
    public ResponseEntity<Boolean> deleteTable(@PathVariable String schema, @PathVariable String table) {
        return ResponseEntity.ok(dingoClient.dropTable(schema, table));
    }

    @ApiOperation("Get table")
    @GetMapping("/api/{schema}/{table}")
    public ResponseEntity<Table> get(@PathVariable String schema, @PathVariable String table) {
        return ResponseEntity.ok(dingoClient.getTableDefinition(schema, table));
    }
}
