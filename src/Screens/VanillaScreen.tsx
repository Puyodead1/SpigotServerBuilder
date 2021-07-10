import React from 'react';
import Logo from '../../assets/img/vanilla.png';

export default function VanillaScreen() {
  return (
    <div>
      <img src={Logo} alt="Logo" width={'128'} />
      <h1>Vanilla</h1>
    </div>
  );
}
