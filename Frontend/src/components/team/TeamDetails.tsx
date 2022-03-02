import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { RootState } from "../../store/combine-store";
import { tTeamWithUsers } from "../../types/team";
import RealTeamDetails from "./RealTeamDetails";
import UserTeamDetails from "./UserTeamDetails";

const TeamDetails: React.FC = () => {

    const { teams } = useSelector((state: RootState) => {
        return {
            teams: state.team.teams
        };
    });

    const [selectedTeam, setSelectedTeam] = useState<tTeamWithUsers>();

    const id = useParams().id;

    useEffect(() => {

        if (id) {
            const team = teams?.find(team => team.id === +id);
            if (team) {
                setSelectedTeam(team)
            }
        }

    }, [teams, id]);

    return <>
        <h3>{selectedTeam?.teamName}</h3>
        {selectedTeam?.playerTeam === true && <UserTeamDetails team={selectedTeam} />}
        {selectedTeam?.playerTeam === false && <RealTeamDetails team={selectedTeam} />}
    </>;
};

export default TeamDetails;