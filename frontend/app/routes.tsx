import React from "react";
import {Route, Routes} from "react-router-dom";
import {TablePage} from "./views/TablePage";
import {CreateDragonPage} from "./views/CreateDragonPage";
import {DragonMenu} from "./containers/SideBar";

export const ApplicationRoutes: React.FC = () => {
    return (
        <Routes>
            <Route path="/create" element={<DragonMenu children={CreateDragonPage()}/>}/>
            <Route path="*" element={<DragonMenu children={TablePage()}/>}/>
        </Routes>
    );
};