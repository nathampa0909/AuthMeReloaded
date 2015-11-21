package fr.xephi.authme.command.executable.login;

import fr.xephi.authme.AuthMe;
import fr.xephi.authme.AuthMeMockUtil;
import fr.xephi.authme.command.CommandParts;
import fr.xephi.authme.process.Management;
import fr.xephi.authme.settings.Settings;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

/**
 * Test for {@link LoginCommand}.
 */
public class LoginCommandTest {

    private static Management managementMock;

    @Before
    public void initializeAuthMeMock() {
        AuthMeMockUtil.mockAuthMeInstance();
        AuthMe pluginMock = AuthMe.getInstance();

        Settings.captchaLength = 10;
        managementMock = mock(Management.class);
        Mockito.when(pluginMock.getManagement()).thenReturn(managementMock);
    }

    @Test
    public void shouldStopIfSenderIsNotAPlayer() {
        // given
        CommandSender sender = mock(BlockCommandSender.class);
        LoginCommand command = new LoginCommand();

        // when
        command.executeCommand(sender, new CommandParts(), new CommandParts());

        // then
        Mockito.verify(managementMock, never()).performLogin(any(Player.class), anyString(), anyBoolean());
    }

    @Test
    public void shouldCallManagementForPlayerCaller() {
        // given
        Player sender = mock(Player.class);
        LoginCommand command = new LoginCommand();

        // when
        command.executeCommand(sender, new CommandParts(), new CommandParts("password"));

        // then
        Mockito.verify(managementMock).performLogin(eq(sender), eq("password"), eq(false));
    }

    @Test
    public void shouldHandleMissingPassword() {
        // given
        Player sender = mock(Player.class);
        LoginCommand command = new LoginCommand();

        // when
        command.executeCommand(sender, new CommandParts(), new CommandParts());

        // then
        // TODO ljacqu 20151121: May make sense to handle null password in LoginCommand instead of forwarding the call
        String password = null;
        Mockito.verify(managementMock).performLogin(eq(sender), eq(password), eq(false));
    }
}