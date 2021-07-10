import React from 'react';
import Logo from '../../assets/icons/128x128.png';

export default function SettingsScreen({ theme, setTheme }: any) {
  return (
    <div className="settings-content">
      <div className="settings-panel">
        <img src={Logo} alt="Logo" />
        <h1>Minecraft Server Builder</h1>
        <h2>Version 3.0.0</h2>
        <p>Electron: N/A</p>
        <p>Node: N/A</p>
        <p>React: N/A</p>
      </div>

      <hr />

      <div className="settings-panel">
        <h4>Theme</h4>
        <div className="radio-container" role="radiogroup">
          <div className="radio">
            <input
              type="radio"
              name="light"
              id="light"
              checked={theme === 'dark' ? false : true}
              onClick={() => setTheme('light')}
            />
            <label htmlFor="light">Light</label>
          </div>
          <div className="radio">
            <input
              type="radio"
              name="dark"
              id="dark"
              checked={theme === 'dark' ? true : false}
              onClick={() => setTheme('dark')}
            />
            <label htmlFor="dark">Dark</label>
          </div>
        </div>
      </div>
    </div>
  );
}
