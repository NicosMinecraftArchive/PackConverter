/*
 * Copyright (c) 2019-2020 GeyserMC. http://geysermc.org
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 *
 *  @author GeyserMC
 *  @link https://github.com/GeyserMC/PackConverter
 *
 */

package org.geysermc.packconverter.api.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

/**
 * author: NukkitX
 * Nukkit Project
 */
@Setter
@Getter
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourcePackManifest {
    @JsonProperty("format_version")
    private Integer formatVersion;
    private Header header;
    private Collection<Module> modules;
    protected Collection<Dependency> dependencies;

    public Collection<Module> getModules() {
        return Collections.unmodifiableCollection(modules);
    }

    @Setter
    @Getter
    @ToString
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Header {
        private String description;
        private String name;
        private UUID uuid;
        private int[] version;
        @JsonProperty("min_engine_version")
        private int[] minimumSupportedMinecraftVersion;
    }

    @Setter
    @Getter
    @ToString
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Module {
        private String description;
        private String type;
        private UUID uuid;
        private int[] version;
    }

    @Setter
    @Getter
    @ToString
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Dependency {
        private UUID uuid;
        private int[] version;
    }

    @Value
    public static class Version {
        int major;
        int minor;
        int patch;

        public static Version fromString(String ver) {
            String[] split = ver.replace(']', ' ')
                    .replace('[', ' ')
                    .replaceAll(" ", "").split(",");

            return new Version(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        }

        public static Version fromArray(int[] ver) {
            return new Version(ver[0], ver[1], ver[2]);
        }

        private Version(int major, int minor, int patch) {
            this.major = major;
            this.minor = minor;
            this.patch = patch;
        }


        @Override
        public String toString() {
            return major + "." + minor + "." + patch;
        }
    }
}
