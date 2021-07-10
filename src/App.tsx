import React, { useEffect, useState } from 'react';
import SpigotLogo from '../assets/img/spigot.png';
import PaperSpigotLogo from '../assets/img/paper.png';
import VanillaLogo from '../assets/img/vanilla.png';
import SpongeForgeLogo from '../assets/img/sponge_forge.png';
import SpongeVanillaLogo from '../assets/img/sponge_vanilla.png';
import GlowstoneLogo from '../assets/img/glowstone.png';
import HomeScreen from './Screens/HomeScreen';
import './App.global.css';
import SpigotScreen from './Screens/SpigotScreen';
import BungeeCordScreen from './Screens/BungeecordScreen';
import PaperSpigotScreen from './Screens/PaperSpigotScreen';
import SpongeForgeScreen from './Screens/SpongeForgeScreen';
import SpongeVanillaScreen from './Screens/SpongeVanillaScreen';
import GlowstoneScreen from './Screens/GlowstoneScreen';
import TravertineScreen from './Screens/TravertineScreen';
import VanillaScreen from './Screens/VanillaScreen';
import WaterfallScreen from './Screens/WaterfallScreen';
import SettingsScreen from './Screens/SettingsScreen';

export default function App() {
  const [theme, setTheme] = useState('dark');
  const [selectedPage, setSelectedPage] = useState('home');
  const [page, setPage] = useState(<HomeScreen />);

  useEffect(() => {
    const html = document.getElementById('html');
    if (html) html.setAttribute('data-theme', theme);
  }, [theme]);

  useEffect(() => {
    switch (selectedPage) {
      case 'bungeecord':
        setPage(<BungeeCordScreen />);
        break;
      case 'glowstone':
        setPage(<GlowstoneScreen />);
        break;
      case 'paperspigot':
        setPage(<PaperSpigotScreen />);
        break;
      case 'spigot':
        setPage(<SpigotScreen />);
        break;
      case 'spongeforge':
        setPage(<SpongeForgeScreen />);
        break;
      case 'spongevanilla':
        setPage(<SpongeVanillaScreen />);
        break;
      case 'travertine':
        setPage(<TravertineScreen />);
        break;
      case 'vanilla':
        setPage(<VanillaScreen />);
        break;
      case 'waterfall':
        setPage(<WaterfallScreen />);
        break;
      case 'settings':
        setPage(<SettingsScreen theme={theme} setTheme={setTheme} />);
        break;
    }
  }, [selectedPage]);

  return (
    <div id="root2">
      <div className="sidenav">
        <a
          href="#"
          className="item active"
          onClick={() => {
            setSelectedPage('bungeecord');
          }}
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 512 512"
            className="svg-icon svg-icon-64"
          >
            <path d="M464 64H48C21.49 64 0 85.49 0 112v288c0 26.51 21.49 48 48 48h416c26.51 0 48-21.49 48-48V112c0-26.51-21.49-48-48-48zm16 336c0 8.822-7.178 16-16 16H48c-8.822 0-16-7.178-16-16V112c0-8.822 7.178-16 16-16h416c8.822 0 16 7.178 16 16v288zM112 232c30.928 0 56-25.072 56-56s-25.072-56-56-56-56 25.072-56 56 25.072 56 56 56zm0-80c13.234 0 24 10.766 24 24s-10.766 24-24 24-24-10.766-24-24 10.766-24 24-24zm207.029 23.029L224 270.059l-31.029-31.029c-9.373-9.373-24.569-9.373-33.941 0l-88 88A23.998 23.998 0 0 0 64 344v28c0 6.627 5.373 12 12 12h360c6.627 0 12-5.373 12-12v-92c0-6.365-2.529-12.47-7.029-16.971l-88-88c-9.373-9.372-24.569-9.372-33.942 0zM416 352H96v-4.686l80-80 48 48 112-112 80 80V352z" />
          </svg>
          <span>BungeeCord</span>
        </a>

        <a
          href="#"
          className="item"
          onClick={() => {
            setSelectedPage('glowstone');
          }}
        >
          <img src={GlowstoneLogo} alt="Glowstone Logo" width="64" />
          <span>Glowstone</span>
        </a>

        <a
          href="#"
          className="item"
          onClick={() => {
            setSelectedPage('paperspigot');
          }}
        >
          <img src={PaperSpigotLogo} alt="PaperSpigot Logo" width="64" />
          <span>PaperSpigot</span>
        </a>

        <a
          href="#"
          className="item"
          onClick={() => {
            setSelectedPage('spigot');
          }}
        >
          <img src={SpigotLogo} alt="Spigot Logo" width="64" />
          <span>Spigot</span>
        </a>

        <a
          href="#"
          className="item"
          onClick={() => {
            setSelectedPage('spongevanilla');
          }}
        >
          <img src={SpongeVanillaLogo} alt="SpongeVanilla Logo" width="70" />
          <span>SpongeVanilla</span>
        </a>

        <a
          href="#"
          className="item"
          onClick={() => {
            setSelectedPage('spongeforge');
          }}
        >
          <img src={SpongeForgeLogo} alt="SpongeForge Logo" width="70" />
          <span>SpongeForge</span>
        </a>

        <a
          href="#"
          className="item"
          onClick={() => {
            setSelectedPage('travertine');
          }}
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 512 512"
            className="svg-icon svg-icon-64"
          >
            <path d="M464 64H48C21.49 64 0 85.49 0 112v288c0 26.51 21.49 48 48 48h416c26.51 0 48-21.49 48-48V112c0-26.51-21.49-48-48-48zm16 336c0 8.822-7.178 16-16 16H48c-8.822 0-16-7.178-16-16V112c0-8.822 7.178-16 16-16h416c8.822 0 16 7.178 16 16v288zM112 232c30.928 0 56-25.072 56-56s-25.072-56-56-56-56 25.072-56 56 25.072 56 56 56zm0-80c13.234 0 24 10.766 24 24s-10.766 24-24 24-24-10.766-24-24 10.766-24 24-24zm207.029 23.029L224 270.059l-31.029-31.029c-9.373-9.373-24.569-9.373-33.941 0l-88 88A23.998 23.998 0 0 0 64 344v28c0 6.627 5.373 12 12 12h360c6.627 0 12-5.373 12-12v-92c0-6.365-2.529-12.47-7.029-16.971l-88-88c-9.373-9.372-24.569-9.372-33.942 0zM416 352H96v-4.686l80-80 48 48 112-112 80 80V352z" />
          </svg>
          <span>Travertine</span>
        </a>

        <a
          href="#"
          className="item"
          onClick={() => {
            setSelectedPage('vanilla');
          }}
        >
          <img src={VanillaLogo} alt="Vanilla Logo" width="64" />
          <span>Vanilla</span>
        </a>

        <a
          href="#"
          className="item"
          onClick={() => {
            setSelectedPage('waterfall');
          }}
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 512 512"
            className="svg-icon svg-icon-64"
          >
            <path d="M464 64H48C21.49 64 0 85.49 0 112v288c0 26.51 21.49 48 48 48h416c26.51 0 48-21.49 48-48V112c0-26.51-21.49-48-48-48zm16 336c0 8.822-7.178 16-16 16H48c-8.822 0-16-7.178-16-16V112c0-8.822 7.178-16 16-16h416c8.822 0 16 7.178 16 16v288zM112 232c30.928 0 56-25.072 56-56s-25.072-56-56-56-56 25.072-56 56 25.072 56 56 56zm0-80c13.234 0 24 10.766 24 24s-10.766 24-24 24-24-10.766-24-24 10.766-24 24-24zm207.029 23.029L224 270.059l-31.029-31.029c-9.373-9.373-24.569-9.373-33.941 0l-88 88A23.998 23.998 0 0 0 64 344v28c0 6.627 5.373 12 12 12h360c6.627 0 12-5.373 12-12v-92c0-6.365-2.529-12.47-7.029-16.971l-88-88c-9.373-9.372-24.569-9.372-33.942 0zM416 352H96v-4.686l80-80 48 48 112-112 80 80V352z" />
          </svg>
          <span>Waterfall</span>
        </a>

        <hr />

        <a
          href="#"
          id="btn-settings"
          className="item zero-padding flex-justify-center"
          onClick={() => setSelectedPage('settings')}
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 512 512"
            className="svg-icon svg-icon-32"
          >
            <path d="M452.515 237l31.843-18.382c9.426-5.441 13.996-16.542 11.177-27.054-11.404-42.531-33.842-80.547-64.058-110.797-7.68-7.688-19.575-9.246-28.985-3.811l-31.785 18.358a196.276 196.276 0 0 0-32.899-19.02V39.541a24.016 24.016 0 0 0-17.842-23.206c-41.761-11.107-86.117-11.121-127.93-.001-10.519 2.798-17.844 12.321-17.844 23.206v36.753a196.276 196.276 0 0 0-32.899 19.02l-31.785-18.358c-9.41-5.435-21.305-3.877-28.985 3.811-30.216 30.25-52.654 68.265-64.058 110.797-2.819 10.512 1.751 21.613 11.177 27.054L59.485 237a197.715 197.715 0 0 0 0 37.999l-31.843 18.382c-9.426 5.441-13.996 16.542-11.177 27.054 11.404 42.531 33.842 80.547 64.058 110.797 7.68 7.688 19.575 9.246 28.985 3.811l31.785-18.358a196.202 196.202 0 0 0 32.899 19.019v36.753a24.016 24.016 0 0 0 17.842 23.206c41.761 11.107 86.117 11.122 127.93.001 10.519-2.798 17.844-12.321 17.844-23.206v-36.753a196.34 196.34 0 0 0 32.899-19.019l31.785 18.358c9.41 5.435 21.305 3.877 28.985-3.811 30.216-30.25 52.654-68.266 64.058-110.797 2.819-10.512-1.751-21.613-11.177-27.054L452.515 275c1.22-12.65 1.22-25.35 0-38zm-52.679 63.019l43.819 25.289a200.138 200.138 0 0 1-33.849 58.528l-43.829-25.309c-31.984 27.397-36.659 30.077-76.168 44.029v50.599a200.917 200.917 0 0 1-67.618 0v-50.599c-39.504-13.95-44.196-16.642-76.168-44.029l-43.829 25.309a200.15 200.15 0 0 1-33.849-58.528l43.819-25.289c-7.63-41.299-7.634-46.719 0-88.038l-43.819-25.289c7.85-21.229 19.31-41.049 33.849-58.529l43.829 25.309c31.984-27.397 36.66-30.078 76.168-44.029V58.845a200.917 200.917 0 0 1 67.618 0v50.599c39.504 13.95 44.196 16.642 76.168 44.029l43.829-25.309a200.143 200.143 0 0 1 33.849 58.529l-43.819 25.289c7.631 41.3 7.634 46.718 0 88.037zM256 160c-52.935 0-96 43.065-96 96s43.065 96 96 96 96-43.065 96-96-43.065-96-96-96zm0 144c-26.468 0-48-21.532-48-48 0-26.467 21.532-48 48-48s48 21.533 48 48c0 26.468-21.532 48-48 48z" />
          </svg>
          <span>Settings</span>
        </a>
      </div>
      <div className="content">{page}</div>
    </div>
  );
}
