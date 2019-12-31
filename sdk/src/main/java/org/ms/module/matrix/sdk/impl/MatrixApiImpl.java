package org.ms.module.matrix.sdk.impl;

import org.ms.module.supper.inter.matrix.IApplicationServiceRoomDirectoryManagement;
import org.ms.module.supper.inter.matrix.ICapabilities;
import org.ms.module.supper.inter.matrix.IDeviceManagement;
import org.ms.module.supper.inter.matrix.IEndToEndEncryption;
import org.ms.module.supper.inter.matrix.IMatrixApi;
import org.ms.module.supper.inter.matrix.IMedia;
import org.ms.module.supper.inter.matrix.IOpenID;
import org.ms.module.supper.inter.matrix.IPresence;
import org.ms.module.supper.inter.matrix.IPushNotifications;
import org.ms.module.supper.inter.matrix.IReadMarkers;
import org.ms.module.supper.inter.matrix.IReportingContent;
import org.ms.module.supper.inter.matrix.IRoomCreation;
import org.ms.module.supper.inter.matrix.IRoomDirectory;
import org.ms.module.supper.inter.matrix.IRoomDiscovery;
import org.ms.module.supper.inter.matrix.IRoomMembership;
import org.ms.module.supper.inter.matrix.IRoomParticipation;
import org.ms.module.supper.inter.matrix.IRoomUpgrades;
import org.ms.module.supper.inter.matrix.ISearch;
import org.ms.module.supper.inter.matrix.ISendToDeviceMessaging;
import org.ms.module.supper.inter.matrix.IServerAdministration;
import org.ms.module.supper.inter.matrix.ISessionManagement;
import org.ms.module.supper.inter.matrix.IUserData;
import org.ms.module.supper.inter.matrix.IVOIP;

public class MatrixApiImpl  implements IMatrixApi {
    @Override
    public IServerAdministration getServerAdministration() {
        return new ServerAdministrationImpl();
    }

    @Override
    public IUserData getUserData() {
        return new UserDataImpl();
    }

    @Override
    public ICapabilities getCapabilities() {
        return new CapabilitiesImpl();
    }

    @Override
    public IRoomCreation getRoomCreation() {
        return new RoomCreationImpl();
    }

    @Override
    public IDeviceManagement getDeviceManagement() {
        return new DeviceManagementImpl();
    }

    @Override
    public IApplicationServiceRoomDirectoryManagement getApplicationServiceRoomDirectoryManagement() {
        return new ApplicationServiceRoomDirectoryManagementImpl();
    }

    @Override
    public IRoomDirectory getRoomDirectory() {
        return new RoomDirectoryImpl();
    }

    @Override
    public IRoomParticipation getRoomParticipation() {
        return new RoomParticipationImpl();
    }

    @Override
    public IRoomMembership getRoomMembership() {
        return new RoomMembershipImpl();
    }

    @Override
    public IEndToEndEncryption getEndToEndEncryption() {
        return new EndToEndEncryptionImpl();
    }

    @Override
    public ISessionManagement getSessionManagement() {
        return new SessionManagementImpl();
    }

    @Override
    public IPushNotifications getPushNotifications() {
        return new PushNotificationsImpl();
    }

    @Override
    public IPresence getPresence() {
        return new PresenceImpl();
    }

    @Override
    public IRoomDiscovery getRoomDiscovery() {
        return new RoomDiscoveryImpl();
    }

    @Override
    public IReadMarkers getReadMarkers() {
        return new ReadMarkersImpl();
    }

    @Override
    public IReportingContent getReportingContent() {
        return new ReportingContentImpl();
    }

    @Override
    public IRoomUpgrades getRoomUpgrades() {
        return new RoomUpgradesImpl();
    }

    @Override
    public ISearch getSearch() {
        return new SearchImpl();
    }

    @Override
    public ISendToDeviceMessaging getSendToDeviceMessaging() {
        return new SendToDeviceMessagingImpl();
    }

    @Override
    public IOpenID getOpenId() {
        return new OpenIDImpl();
    }

    @Override
    public IVOIP getVoIP() {
        return new VOIPImpl();
    }

    @Override
    public IMedia getMedia() {
        return new MediaImpl();
    }
}
