package io.github.sternstaub.civitasrpg.flags.interfaces;

import io.github.sternstaub.civitasrpg.flags.interfaces.config.AbstractConfigFlag;
import io.github.sternstaub.civitasrpg.interfaces.CivitasFlag;

public interface AbstractIngameFlag extends CivitasFlag {

    /**
     * GameObject flags are a way to bridge Config flags into the plugin.
     * so, every gameObject flag will be connected to a configFlag (which is used  to specify config entries)
     * @return the config flag that is used to read the value of this GameObject flag
     */
    abstract AbstractConfigFlag getConfigFlag();

}
