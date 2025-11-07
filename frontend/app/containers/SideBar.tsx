import {Button, Overlay, Sidebar, type SidebarState, useSidebar} from '@rewind-ui/core';
import {type ReactNode, useState} from "react";
import {BsUpload} from "../icons/BsUpload";
import {BsHome} from "../icons/BsHome";

interface DragonMenuInterface {
    children: ReactNode
}

export const DragonMenu = ({children}: DragonMenuInterface) => {
    const [expanded, setExpanded] = useState(true);
    const [mobile, setMobile] = useState(false);
    const sidebar = useSidebar();

    return (
        <div className="relative flex flex-row w-full h-screen">
            <Sidebar
                onToggle={(state: SidebarState) => {
                    setExpanded(state.expanded);
                    setMobile(state.mobile);
                }}
                className="absolute"
            >
                <Sidebar.Head>
                    <Sidebar.Head.Title>Dragons Application</Sidebar.Head.Title>
                    <Sidebar.Head.Toggle/>
                </Sidebar.Head>
                <Sidebar.Nav>

                    <Sidebar.Nav.Section>
                        <Sidebar.Nav.Section.Item icon={<BsUpload/>} label="Create new dragon" href="/create"/>
                        <Sidebar.Nav.Section.Item icon={<BsHome/>} label="Main page" href="/"/>
                    </Sidebar.Nav.Section>
                </Sidebar.Nav>
            </Sidebar>

            <main
                className={`transition-all transform duration-100 text-slate-700 flex w-full flex-col items-center ${
                    expanded ? 'md:ml-64' : 'md:ml-20'
                }`}
            >
                {mobile && (
                    <Overlay
                        blur="none"
                        onClick={() => {
                            sidebar.toggleMobile();
                        }}
                        className="md:hidden z-40"
                    />
                )}
                <header
                    className="flex flex-row sticky top-0 px-8 items-center bg-white border-b border-b-gray-100 w-full shadow-sm min-h-[4rem]">
                    <span></span>

                    <Button
                        onClick={() => {
                            sidebar.toggleMobile();
                        }}
                        size="sm"
                        color="white"
                        icon
                        className="ml-auto flex md:hidden"
                    >
                        <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512">
                            <path
                                d="M448 96c0-17.7-14.3-32-32-32H32C14.3 64 0 78.3 0 96s14.3 32 32 32H416c17.7 0 32-14.3 32-32zm0 320c0-17.7-14.3-32-32-32H32c-17.7 0-32 14.3-32 32s14.3 32 32 32H416c17.7 0 32-14.3 32-32z"/>
                            <path
                                className="opacity-50"
                                d="M0 256c0-17.7 14.3-32 32-32H416c17.7 0 32 14.3 32 32s-14.3 32-32 32H32c-17.7 0-32-14.3-32-32z"
                            />
                        </svg>
                    </Button>
                </header>

                {children}
            </main>
        </div>
    )
}
