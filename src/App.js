import React, { useState } from 'react';
import './App.css';
import ImageGenerator from './components/ImageGenerator';
import RecipeGenerator from './components/RecipeGenerator';
import ChatComponent from './components/ChatComponent';
function App() {
  const [activeTab,SetActiveTab] = useState('image-generator');

  const handleTabChange =(tab) =>{
    //alert(tab)
    SetActiveTab(tab);
  } 
  return (
    <div className="App">
      <div className="button-group">
      <button className={activeTab==='image-generator'?'active' : ''}
      onClick={() => handleTabChange('image-generator')}>
        Image Generator
        </button>
      <button className={activeTab==='chat'?'active' : ''}
      onClick={() => handleTabChange('chat')}>
        Chat
        </button>
      <button className={activeTab==='recipe-generator'?'active' : ''}
      onClick={() => handleTabChange('recipe-generator')}>
        Recipe Generator
        </button>
      </div>
        <div>
          {activeTab === 'image-generator' && <ImageGenerator/>}
           {activeTab === 'chat' && <ChatComponent/>}
            {activeTab === 'recipe-generator' && <RecipeGenerator/>}
        </div>

    </div>
  );
}

export default App;
