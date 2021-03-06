package org.hidetake.groovy.ssh.extension

import org.hidetake.groovy.ssh.extension.settings.LocalPortForwardSettings
import org.hidetake.groovy.ssh.extension.settings.RemotePortForwardSettings
import org.hidetake.groovy.ssh.session.SessionExtension

/**
 * An extension class of port forwarding.
 *
 * @author Hidetake Iwata
 */
trait PortForward implements SessionExtension {

    /**
     * Forwards local port to remote port.
     *
     * @param settings {@see LocalPortForwardingSettings}
     * @return local port
     */
    int forwardLocalPort(HashMap settings) {
        assert settings != null, 'settings must not be null'
        def merged = LocalPortForwardSettings.DEFAULT + new LocalPortForwardSettings(settings)
        assert merged.hostPort, 'remote port must be given'
        operations.forwardLocalPort(merged)
    }

    /**
     * Forwards remote port to local port.
     *
     * @param settings {@see RemotePortForwardingSettings}
     */
    void forwardRemotePort(HashMap settings) {
        assert settings != null, 'settings must not be null'
        def merged = RemotePortForwardSettings.DEFAULT + new RemotePortForwardSettings(settings)
        assert merged.hostPort, 'local port must be given'
        assert merged.port, 'remote port must be given'
        operations.forwardRemotePort(merged)
    }

}