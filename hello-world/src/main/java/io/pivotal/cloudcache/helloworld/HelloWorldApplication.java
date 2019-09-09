/*
 * Cloud Cache Examples
 *
 * Copyright (c) 2019-Present Pivotal Software, Inc. All Rights Reserved.
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License"). You may not use this product except in compliance with the License.
 *
 * This product may include a number of subcomponents with separate copyright notices and license terms. Your use of these subcomponents is subject to the terms and conditions of the subcomponent's license, as noted in the LICENSE file.
 */

package io.pivotal.cloudcache.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.gemfire.config.annotation.EnableCachingDefinedRegions;
import org.springframework.data.gemfire.config.annotation.EnableClusterConfiguration;

@SpringBootApplication
// Causes the creation of server-side Cloud Cache/GemFire regions via the @Cacheable annotation during the
// Initialization phase of the app's lifecycle
@EnableCachingDefinedRegions
public class HelloWorldApplication {

	@Configuration
	@Profile("!localCluster")
	// Allows Spring to configure the Cloud Cache/GemFire cluster; necessary for creating regions
	@EnableClusterConfiguration(useHttp = true)
	static class CloudConfiguration {

	}

	@Configuration
	@Profile("localCluster")
	@EnableClusterConfiguration(useHttp = true, requireHttps = false)
	static class LocalConfiguration {

	}

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}
}
